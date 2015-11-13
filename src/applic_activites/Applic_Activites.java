/**
 * Applic_Activites
 *
 * Copyright (C) 2012 Sh1fT
 *
 * This file is part of Applic_Activites.
 *
 * Applic_Activites is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * Applic_Activites is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Applic_Activites; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package applic_activites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import org.xml.sax.InputSource;
import utils.PropertiesLauncher;

/**
 * Manage a {@link Applic_Activites}
 * @author Sh1fT
 */
public class Applic_Activites {
    private PropertiesLauncher propertiesLauncher;

    /**
     * Create a new {@link Applic_Activites} instance
     */
    public Applic_Activites() {
        this.setPropertiesLauncher(new PropertiesLauncher(
                System.getProperty("file.separator") + "properties" +
                System.getProperty("file.separator") + "Applic_Activites.properties"));
    }

    /**
     * Execute a Command
     * @param args
     * @return 
     */
    public String sendCmd(String[] args) {
        try {
            Socket socket = new Socket(this.getServerAddress(), this.getServerPort());
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            InputSource is = new InputSource(new InputStreamReader(socket.getInputStream()));
            BufferedReader br = new BufferedReader(is.getCharacterStream());
            String assembledCmd = "";
            for (String arg : args)
                assembledCmd += arg + ":";
            pw.println(assembledCmd);
            String response = br.readLine();
            br.close();
            pw.close();
            socket.close();
            return response;
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    public PropertiesLauncher getPropertiesLauncher() {
        return propertiesLauncher;
    }

    public void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
        this.propertiesLauncher = propertiesLauncher;
    }

    public Properties getProperties() {
        return this.getPropertiesLauncher().getProperties();
    }

    public String getServerAddress() {
        return this.getProperties().getProperty("serverAddress");
    }

    public Integer getServerPort() {
        return Integer.parseInt(this.getProperties().getProperty("serverPort"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Applic_Activites aa = new Applic_Activites();
            String cmd = ".4S&v";
            String cmdTemp = "";
            String response = "";
            String name = "";
            String password = "";
            String type = "";
            String date = "";
            String length = "";
            String clientName = "";
            Boolean logged = false;
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            do {
                if (cmd.equals(".4S&v")) {
                    System.out.println("Veuillez entrer une commande: ");
                    cmd = br.readLine();
                }
                if (!logged) {
                    cmdTemp = cmd;
                    cmd = "LOGIN";
                }
                switch (cmd) {
                    case "LOGIN":
                        System.out.println("~ Identification ~");
                        System.out.println("Name: ");
                        name = br.readLine();
                        System.out.println("Password: ");
                        password = br.readLine();
                        response = aa.sendCmd(new String[] {cmd, name, password});
                        switch (response) {
                            case "OK":
                                System.out.println("Login successfully executed :)");
                                logged = true;
                                if (!cmdTemp.equals(""))
                                    cmd = cmdTemp;
                                else
                                    cmd = ".4S&v";
                                break;
                            case "KO":
                                System.out.println("Login not successfully executed :(");
                                cmd = ".4S&v";
                                break;
                            default:
                                break;
                        }
                       break;
                    case "BACTFUN":
                        System.out.println("~ Booking Funny Activities ~");
                        System.out.println("Type: ");
                        type = br.readLine();
                        System.out.println("Date (dd/mm/yyyy): ");
                        date = br.readLine();
                        System.out.println("Client name: ");
                        clientName = br.readLine();
                        response = aa.sendCmd(new String[] {cmd, type, date,
                            clientName});
                        switch (response) {
                            case "OK":
                                System.out.println("Booking successfully executed :)");
                                break;
                            case "KO":
                                System.out.println("Booking not successfully executed :(");
                                break;
                            default:
                                break;
                        }
                        cmd = ".4S&v";
                        break;
                    case "ACKACTFUN":
                        System.out.println("~ Acknowledgement Funny Activities ~");
                        System.out.println("Type: ");
                        type = br.readLine();
                        System.out.println("Date (dd/mm/yyyy): ");
                        date = br.readLine();
                        System.out.println("Client name: ");
                        clientName = br.readLine();
                        response = aa.sendCmd(new String[] {cmd, type, date,
                            clientName});
                        switch (response) {
                            case "OK":
                                System.out.println("Booking ack successfully executed :)");
                                break;
                            case "KO":
                                System.out.println("Booking ack not successfully executed :(");
                                break;
                            default:
                                break;
                        }
                        cmd = ".4S&v";
                        break;
                    case "BTREKFUN":
                        System.out.println("~ Booking Funny Trek ~");
                        System.out.println("Type: ");
                        type = br.readLine();
                        System.out.println("Date (dd/mm/yyyy): ");
                        date = br.readLine();
                        System.out.println("Length (in days): ");
                        length = br.readLine();
                        System.out.println("Client name: ");
                        clientName = br.readLine();
                        response = aa.sendCmd(new String[] {cmd, type, date,
                            length, clientName});
                        switch (response) {
                            case "OK":
                                System.out.println("Booking Funny Treck successfully executed :)");
                                break;
                            case "KO":
                                System.out.println("Booking Funny Treck not successfully executed :(");
                                break;
                            default:
                                break;
                        }
                        cmd = ".4S&v";
                        break;
                }
            } while (cmd != "");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            System.exit(1);
        }
    }
}
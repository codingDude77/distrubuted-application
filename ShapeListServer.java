import java.util.Vector;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;

public class ShapeListServer {

    public static void main(String args[]){

        int port = 23230;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        try {
            Registry registry = LocateRegistry.createRegistry(port);
            ShapeList aShapelist = new ShapeListServant();
            ShapeList stub = (ShapeList) UnicastRemoteObject.exportObject(aShapelist,0);
            registry.rebind("Whiteboard", aShapelist);
            System.out.println("Whiteboard server ready: Port " + port);
        } catch (RemoteException e) {
            System.out.println("Whiteboard server main " + e.getMessage());
        }

    }

}

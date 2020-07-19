
public class ChainedException {
    public static void main(String[] args) {
        try{
            install();
        }catch (InstallException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static void install() throws InstallException{
        try{
            startInstall();
            copyfiles();
        }catch (SpaceException e){
            InstallException ie = new InstallException("Exception while install");
            ie.initCause(e);
            throw ie;
        }catch (MemoryException me){
            InstallException ie = new InstallException("Exception while install");
            ie.initCause(me);
            throw ie;
        }finally {
            deleteTemps();
        }
    }
    static void startInstall() throws SpaceException, MemoryException{
        if(!enoughSpace()){
            throw new SpaceException("Not enough Space");
        }
        if(!enoughMemory()){
            throw new MemoryException("Not enough Memory");
        }
    }
    static void copyfiles(){

    }
    static void deleteTemps(){

    }
    static boolean enoughSpace(){
        return false;
    }
    static boolean enoughMemory(){
        return true;
    }

}
class InstallException extends Exception{
    InstallException(String msg){
        super(msg);
    }
}
class SpaceException extends Exception{
    SpaceException(String msg){
        super(msg);
    }
}
class MemoryException extends Exception{
    MemoryException(String msg){
        super(msg);
    }
}
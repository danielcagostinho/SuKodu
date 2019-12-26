import sukodu.controller.Controller;
import sukodu.model.Board;
import sukodu.view.View;

public class App {

    public static void main(String[] args){
        Board b = new Board();
        View v = new View();
        Controller c = new Controller(b,v);
        c.initController();

    }

}

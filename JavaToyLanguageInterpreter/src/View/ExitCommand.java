package View;


public class ExitCommand extends Command {
    private String key;
    private String description;

    public ExitCommand(String k,String de){
        super(k,de);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}

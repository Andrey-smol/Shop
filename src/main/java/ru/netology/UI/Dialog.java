package ru.netology.UI;

import ru.netology.storage.Categories;

import java.util.ArrayList;
import java.util.List;

public class Dialog {
    private final IDialog dialog;
    List<ICommand> commands = new ArrayList<>();

    public Dialog(IDialog dialog) {
        this.dialog = dialog;
    }

    private void setCommands(){
        commands.add(()->{
            Categories.getInstance().get().stream().forEach(s->{
                System.out.println("Категория - " + s);
            });
        });
        commands.add(()->{});
    }
    public void getCategories(){

    }
}

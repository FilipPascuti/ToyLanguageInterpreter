package view;

import controller.Controller;
import exceptions.*;
import model.ProgramState;
import model.expressions.ArithmeticExpression;
import model.expressions.ValueExpression;
import model.expressions.VarExpression;
import model.statements.*;
import model.types.BooleanType;
import model.types.IntType;
import model.types.StringType;
import model.utilities.ADTs.FileTable;
import model.utilities.ADTs.MyDictionary;
import model.utilities.ADTs.MyList;
import model.utilities.ADTs.MyStack;
import model.values.BooleanValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;
import view.commands.ExitCommand;
import view.commands.RunExample;

public class Interpreter {

    public static void main(String[] args) {


        IStatement statement1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v",
                                new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VarExpression("v"))
                )
        );

        ProgramState state1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement1);
        IRepository repository1 = new Repository(state1, "log1.txt");
        Controller controller1 = new Controller(repository1, true);

        IStatement statement2 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(
                                        new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)),
                                                3),
                                        1)
                                ),
                                new CompoundStatement(
                                        new AssignmentStatement("b",new ArithmeticExpression(
                                                new VarExpression("a"),
                                                new ValueExpression(new IntValue(1)),
                                                1)),
                                        new PrintStatement(new VarExpression("b"))
                                )
                        )
                )
        );

        ProgramState state2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement2);
        IRepository repository2 = new Repository(state2, "log2.txt");
        Controller controller2 = new Controller(repository2, true);

        IStatement statement3 = new CompoundStatement(
                new VariableDeclarationStatement("a", new BooleanType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VarExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStatement(new VarExpression("v"))
                                )
                        )
                )
        );

        ProgramState state3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement3);
        IRepository repository3 = new Repository(state3, "log3.txt");
        Controller controller3 = new Controller(repository3, true);

        IStatement statement4 = new CompoundStatement(
                new VariableDeclarationStatement("file", new StringType()),
                new CompoundStatement(
                        new AssignmentStatement("file", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenRFileStatement(new VarExpression("file")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VarExpression("file"), "v"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VarExpression("v")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VarExpression("file"), "v"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VarExpression("v")),
                                                                        new CloseRFileStatement(new VarExpression("file"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState state4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement4);
        IRepository repository4 = new Repository(state4, "log4.txt");
        Controller controller4 = new Controller(repository4, true);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",statement1.toString(),controller1));
        menu.addCommand(new RunExample("2",statement2.toString(),controller2));
        menu.addCommand(new RunExample("3",statement3.toString(),controller3));
        menu.addCommand(new RunExample("4",statement4.toString(),controller4));

        menu.show();
    }
}

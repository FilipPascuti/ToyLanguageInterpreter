package view;

import controller.Controller;
import model.ProgramState;
import model.expressions.*;
import model.statements.*;
import model.types.BooleanType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.utilities.ADTs.*;
import model.values.BooleanValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
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

        ProgramState state1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement1, new MyHeap<>());
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

        ProgramState state2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement2, new MyHeap<>());
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

        ProgramState state3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement3, new MyHeap<>());
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

        ProgramState state4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement4, new MyHeap<>());
        IRepository repository4 = new Repository(state4, "log4.txt");
        Controller controller4 = new Controller(repository4, true);



        IStatement statement5 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VarExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new PrintStatement(new VarExpression("a"))
                                        )
                                )
                        )
                )
        );

        ProgramState state5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement5, new MyHeap<>());
        IRepository repository5 = new Repository(state5, "log5.txt");

        IStatement statement6 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VarExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new ReadHeapExpression(new VarExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression( new ReadHeapExpression(new ReadHeapExpression(new VarExpression("a"))), new ValueExpression(new IntValue(5)), 1 ))
                                        )
                                )
                        )
                )
        );

        ProgramState state6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement6, new MyHeap<>());
        IRepository repository6 = new Repository(state6, "log6.txt");
        Controller controller6 = new Controller(repository6, true);

        IStatement statement7 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VarExpression("v"))),
                                new CompoundStatement(
                                        new HeapWriteStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        new ReadHeapExpression(new VarExpression("v")),
                                                        new ValueExpression(new IntValue(5)),
                                                        1
                                                )
                                        )
                                )
                        )
                )
        );
        Controller controller5 = new Controller(repository5, true);

        ProgramState state7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement7, new MyHeap<>());
        IRepository repository7 = new Repository(state7, "log7.txt");
        Controller controller7 = new Controller(repository7, true);

        IStatement statement8 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VarExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement( new ReadHeapExpression(new ReadHeapExpression(new VarExpression("a"))))
                                        )
                                )
                        )
                )
        );

        ProgramState state8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement8, new MyHeap<>());
        IRepository repository8 = new Repository(state8, "log8.txt");
        Controller controller8 = new Controller(repository8, true);

        IStatement statement9 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VarExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VarExpression("a"))))
                                        )
                                )
                        )
                )
        );

        ProgramState state9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement9, new MyHeap<>());
        IRepository repository9 = new Repository(state9, "log9.txt");
        Controller controller9 = new Controller(repository9, true);

        IStatement statement10 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                new PrintStatement(new ReadHeapExpression( new VarExpression("v")))
                        )
                )
        );

        ProgramState state10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement10, new MyHeap<>());
        IRepository repository10 = new Repository(state10, "log10.txt");
        Controller controller10 = new Controller(repository10, true);

        IStatement statement11 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(new VarExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                        new CompoundStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new AssignmentStatement("v", new ArithmeticExpression(new VarExpression("v"), new ValueExpression(new IntValue(1)), 2))
                                        )
                                ),
                                new PrintStatement(new VarExpression("v"))
                        )
                )
        );

        ProgramState state11 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new FileTable(), statement11, new MyHeap<>());
        IRepository repository11 = new Repository(state11, "log11.txt");
        Controller controller11 = new Controller(repository11, true);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",statement1.toString(),controller1));
        menu.addCommand(new RunExample("2",statement2.toString(),controller2));
        menu.addCommand(new RunExample("3",statement3.toString(),controller3));
        menu.addCommand(new RunExample("4",statement4.toString(),controller4));
        menu.addCommand(new RunExample("5",statement5.toString(),controller5));
        menu.addCommand(new RunExample("6",statement6.toString(),controller6));
        menu.addCommand(new RunExample("7",statement7.toString(),controller7));
        menu.addCommand(new RunExample("8",statement8.toString(),controller8));
        menu.addCommand(new RunExample("9",statement9.toString(),controller9));
        menu.addCommand(new RunExample("10",statement10.toString(),controller10));
        menu.addCommand(new RunExample("11",statement11.toString(),controller11));
        menu.show();
    }
}

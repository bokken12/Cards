package messaging;

import java.util.ArrayList;

//I'm an example of a message class
public class ExampleMessage extends Message
{
    // let's say I wanted to send a boolean

    boolean badSend;

    // that won't work, you can use normal classes and such but they will not be
    // sent

    StringableBoolean sendBool;

    // this works much better
    // but you still shouldn't use it
    // disregarding the previous we can also send other types of things

    StringableArrayList<StringableInteger> sendInts;

    // like that

    StringableArrayList<Integer> badInts;

    // Stringable lists can only be of other stringables though
    // it won't work just like this though
    // we'll need a constructor
    // you should include a blank constructor
    public ExampleMessage()
    {

    }

    // and one that has useful arguments
    public ExampleMessage(boolean bool, ArrayList<Integer> ints)
    {
        // we can make ourselves stringables from the arguments

        StringableBoolean tempBool = new StringableBoolean(bool);

        // now we have to do something very important

        data.add("myBool", sendBool);
        data.add("String", new StringableString());

        // we must add them to data
        // not how I never actually changed sendBool, that was intentional
        // you should never use variables, because they will not be sent
        // instead just grab stuff from sent

        Stringable gettingItBack = data.get("myBool");

        // You should then be able to cast it as the type you know it is

        StringableBoolean gotten = (StringableBoolean) gettingItBack;
        if (gotten.getBoolean() == tempBool.getBoolean())
        {
            System.out.println("I worked");
        }
    }
}

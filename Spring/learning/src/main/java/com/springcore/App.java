package com.springcore;

import org.springframework.context.annotation.ComponentScan;

import com.springcore.auto.wire.MyRunner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MyRunner.printEmp();
    }
}

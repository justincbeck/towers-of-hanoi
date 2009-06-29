package com.beckproduct.towersofhanoi;

import java.io.*;
import java.util.*;

public class Towers
{
    Stack<Integer> left;

    Stack<Integer> middle;

    Stack<Integer> right;

    Integer blockCount;

    public Towers(Integer blockCount)
    {
        this.blockCount = blockCount;
        this.left = new Stack<Integer>();
        this.middle = new Stack<Integer>();
        this.right = new Stack<Integer>();

        for (int i = blockCount; i > 0; i--)
        {
            left.push(i);
        }
    }

    private void move(Integer blockCount, Stack<Integer> source, Stack<Integer> dest, Stack<Integer> temp)
    {
        if (blockCount == 0)
            return;

        this.print();
        move(blockCount - 1, source, temp, dest);

        dest.push(source.pop());

        this.print();
        move(blockCount - 1, temp, dest, source);
    }

    private void print()
    {
        Iterator<Integer> leftIterator = left.iterator();
        System.out.print("Left:  ");
        while (leftIterator.hasNext())
            System.out.print(leftIterator.next());

        System.out.println();

        Iterator<Integer> middleIterator = middle.iterator();
        System.out.print("Middle:  ");
        while (middleIterator.hasNext())
            System.out.print(middleIterator.next());

        System.out.println();

        Iterator<Integer> rightIterator = right.iterator();
        System.out.print("Right:  ");
        while (rightIterator.hasNext())
            System.out.print(rightIterator.next());

        System.out.println();
    }

    public void solve()
    {
        this.print();
        move(blockCount, left, right, middle);
    }

    public static void main(String[] args)
    {
        Integer blockCount = Integer.parseInt(args[0]);
        Towers towers = new Towers(blockCount);
        System.out.print("This will take " + Double.valueOf(Math.pow(2, blockCount) - 1).intValue() + " moves to complete!  Do you want to continue? ");
        DataInputStream dis = new DataInputStream(System.in);
        try
        {
            while (true)
            {
                int response = dis.read();
                if (response == 'y' || response == 'n')
                {
                    if (response == 'y')
                        towers.solve();

                    System.exit(0);
                }
                dis.skip(response);
                System.out.println("'y' or 'n' please!");
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}

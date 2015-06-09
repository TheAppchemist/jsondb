package com.appchey.jsondb;

import java.util.ArrayList;

public class Query
{
    private String selection;
    private String[] selectionArgs;
    private ArrayList<String> orderBy;
    private Class c;

    public Query(Class c)
    {
        selection = null;
        selectionArgs = null;
        orderBy = new ArrayList<>();
        this.c = c;
    }

    public Query where(String selection, String[] args)
    {
        this.selection = selection;
        this.selectionArgs = args;

        return this;
    }

    public Query orderAsc(String column)
    {
        orderBy.add(column + " ASC");

        return this;
    }

    public Query orderDesc(String column)
    {
        orderBy.add(column + " DESC");

        return this;
    }

    public <T> ArrayList<T> list()
    {
        String orderby = null;
        for (String order : orderBy)
        {
            if (orderby == null)
            {
                orderby = order;
            }
            else
            {
                orderby += ", " + order;
            }
        }

        return JSONDBRecord.list(c,
                selection,
                selectionArgs,
                null,
                null,
                orderby);
    }
}

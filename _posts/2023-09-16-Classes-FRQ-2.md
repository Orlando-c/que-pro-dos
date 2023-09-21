---
comments: True
layout: post
title: Classes FRQ 2
type: hacks
courses: { csa: {'week': 4}}
---

# 2021 FRQ Question 2 on the APCSA Exam

### 

```
public class CombinedTable
{
    private SingleTable table1;
    private SingleTable table2;
    
    public CombinedTable(SingleTable tab1, SingleTable tab2)
    {
        table1 = tab1;
        table2 = tab2;
    }

     public boolean canSeat(int n)
    {
        if (table1.getNumSeats() + table2.getNumSeats() - 2 >=n)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public double getDesireability()
    {
        if (table1.getHeight() == table2.getHeight())
        {
            return (table1.getViewQuality() +
                    table2.getViewQuality()) / 2;
        }
        else
        {
            return (table1.getViewQuality() +
                    table2.getViewQuality()) /2;
        }
    }
}
```
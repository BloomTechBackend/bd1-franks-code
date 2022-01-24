### How does contains decide if an object is in the list?

The Javadoc has some pretty formal language in there, so let's break it down a bit.
 
`...returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e))`

Let's ignore the `null` part and assume that `ArrayList`  handles `null`s properly.
In order to determine if an object, `o`, is in the list, the code loops over each element in the list and asks is
`o.equals(e)`. If it is true, return true.

The pseudocode might look like:
```
for element e in list:
    if o.equals(e):
        return true
return false;
```

This means that the `contains` method relies on the `equals` method. Do we have an `equals` method defined in `Flower`?
If not we are using the inherited `equals` from `Object`.

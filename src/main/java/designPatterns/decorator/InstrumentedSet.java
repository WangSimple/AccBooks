package designPatterns.decorator;

import java.util.Set;

public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount=0;
    public InstrumentedSet(Set<E> s){super(s);}
    @Override
    public boolean add(E e){
        addCount++;
        return super.add(e);
    }
    public int getCount(){
        return addCount;
    }
}

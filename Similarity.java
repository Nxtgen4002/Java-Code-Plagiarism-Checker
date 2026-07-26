import java.util.*;
public class Similarity
{
    public static double jaccard(Set<Integer> a, Set<Integer> b)
    {
        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(b);

        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);

        if(union.isEmpty()) return 0.0;
        return (intersection.size() * 100.0) / union.size();
    }
}
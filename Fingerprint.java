import java.util.*;

public class Fingerprint
{
    public static Set<Integer> fingerprints(List<String> tokens, int k)
    {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<=tokens.size()-k;i++)
        {
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<k;j++)
            {
                sb.append(tokens.get(i + j)).append("#");
            }
            set.add(sb.toString().hashCode());
        }
        return set;
    }
}
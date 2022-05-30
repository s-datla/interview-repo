import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MerchantReaderTest {

    private String content;

    private MerchantReader testMerchantReader = new MerchantReader();;

    @Before
    public void setup(){
        try {
            Path testPath = Path.of("simple-data.csv");
            content = Files.readString(testPath);
        } catch (Exception e){

        }

    }

    @Test
    public void returnsListGroupedByAccountCode(){
        ArrayList<String> testResult = testMerchantReader.groupBy(content);

        Assert.assertEquals(testResult.get(0), "INCOME 9.5411025E8");
        Assert.assertEquals(testResult.get(1), "MERCHANT 1.9082205E9");
    }
}

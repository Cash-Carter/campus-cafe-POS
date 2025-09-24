package edu.lewisu.cashcarter.campuscafepos.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.lewisu.cashcarter.campuscafepos.model.Beverage;
import edu.lewisu.cashcarter.campuscafepos.model.Food;
import edu.lewisu.cashcarter.campuscafepos.model.Product;

public class ProductDeserializer extends JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jsonParser);

        Class<? extends Product> productClass = null;
        String type = root.get("type").asText();
        if (type.equals("food"))
            productClass = Food.class;
        else if (type.equals("beverage"))
            productClass = Beverage.class;
        else
            return null;
        
        root.remove("type");
        
        return mapper.convertValue(root, productClass);
    }
}

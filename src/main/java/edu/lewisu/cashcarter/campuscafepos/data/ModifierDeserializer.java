package edu.lewisu.cashcarter.campuscafepos.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.lewisu.cashcarter.campuscafepos.model.Modifier;
import edu.lewisu.cashcarter.campuscafepos.model.ProductModifier;
import edu.lewisu.cashcarter.campuscafepos.model.SumModifier;

public class ModifierDeserializer extends JsonDeserializer<Modifier> {

    @Override
    public Modifier deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jsonParser);

        Class<? extends Modifier> modifierClass = null;
        String type = root.get("type").asText();
        if (type.equals("sum"))
            modifierClass = SumModifier.class;
        else if (type.equals("product"))
            modifierClass = ProductModifier.class;
        else
            return null;
        
        root.remove("type");
        
        return mapper.convertValue(root, modifierClass);
    }
}

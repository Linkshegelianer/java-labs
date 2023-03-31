package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getName());
        sb.append(" ");
        for (Map.Entry<String, String> entry : getAttributes().entrySet()) {
            sb.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"").append(" ");
        }
        sb = new StringBuilder(sb.toString().trim());
        sb.append(">");

        return sb.toString();
    }
}
// END

package googleAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

public abstract class AbstractSample {
    protected static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(
                Iterables.transform(params.entrySet(), new Function<Entry<String, String>, String>() {

                    @Override
                    public String apply(final Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());// ключ - значення
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодирует строку в

                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }
}
package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Stream;

/**
 * {@link Preferences} 引导程序
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class PreferencesBootstrap {

    public static void main(String[] args) throws BackingStoreException {

        Preferences preferences = Preferences.systemRoot().node("/mercyblitz");

        Stream.of(preferences.childrenNames()).forEach(System.out::println);

        preferences.put("name", "小马哥");

        preferences.sync();

        System.out.println(preferences.get("name", ""));



    }
}

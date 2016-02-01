package maseno.online.examination.system.client;

import com.alee.laf.WebLookAndFeel;
import javax.swing.UIManager;

public class MasenoOnlineExaminationSystemClient {

    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        ClientLogin.main(new String[]{});
    }
}

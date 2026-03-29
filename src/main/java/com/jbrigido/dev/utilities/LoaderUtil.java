package com.jbrigido.dev.utilities;

import com.jbrigido.dev.components.ALoader;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class LoaderUtil {

    public static void runWithLoader(JFrame parent, Runnable doBackground, Runnable done) {
        ALoader loader = new ALoader(parent, true);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (doBackground != null) {
                    doBackground.run();
                }
                return null;
            }

            @Override
            protected void done() {
                if (done != null) {
                    done.run();
                }
                loader.setVisible(false);
            }

        };

        worker.execute();

        loader.setVisible(true);

    }

}

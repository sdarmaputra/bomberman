/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package msg;

import java.io.Serializable;

/**
 *
 * @author baskoro
 */
public class Message implements Serializable {
    private String dari, untuk, isi;

    /**
     * @return the dari
     */
    public String getDari() {
        return dari;
    }

    /**
     * @param dari the dari to set
     */
    public void setDari(String dari) {
        this.dari = dari;
    }

    /**
     * @return the untuk
     */
    public String getUntuk() {
        return untuk;
    }

    /**
     * @param untuk the untuk to set
     */
    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    /**
     * @return the isi
     */
    public String getIsi() {
        return isi;
    }

    /**
     * @param isi the isi to set
     */
    public void setIsi(String isi) {
        this.isi = isi;
    }
}

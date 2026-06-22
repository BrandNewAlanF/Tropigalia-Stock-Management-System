import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validador {

    public static boolean campoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean comboValido(JComboBox<?> combo) {
        return combo.getSelectedIndex() > 0;
    }

    public static boolean dataValida(String data) {

        try {

            SimpleDateFormat sdf =
                    new SimpleDateFormat("dd/MM/yyyy");

            sdf.setLenient(false);

            Date d = sdf.parse(data.trim());

            Calendar cal = Calendar.getInstance();

            cal.setTime(d);

            int ano = cal.get(Calendar.YEAR);

            return ano >= 2026;

        } catch (Exception e) {

            return false;
        }
    }

    public static boolean numeroPositivo(String texto) {

        try {

            return Integer.parseInt(texto.trim()) > 0;

        } catch (Exception e) {

            return false;
        }
    }
}
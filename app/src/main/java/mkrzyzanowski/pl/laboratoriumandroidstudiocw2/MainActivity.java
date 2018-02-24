package mkrzyzanowski.pl.laboratoriumandroidstudiocw2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nazwaText;
    EditText numerText;
    EditText delNumer;
    TextView textBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nazwaText = findViewById(R.id.nazwaText);
        numerText = findViewById(R.id.numerText);
        textBox = findViewById(R.id.textBox);
        delNumer = findViewById(R.id.delNumer);
        textBox.setMovementMethod(new ScrollingMovementMethod());
        SugarContext.init(this);
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());
    }

    public void addData(View view) {
        String nazwa = nazwaText.getText().toString();
        String numer = numerText.getText().toString();
        if (!nazwa.equals("") || !numer.equals("")) {
            Kontakt kontakt = new Kontakt(nazwa, numer);
            kontakt.save();
        } else {
            showWrongCredentialsDialog("Błąd", "Wprowadź przynajmniej jedną dane");
        }
    }
    public void showWrongCredentialsDialog(String title, String text){
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    public void updateData(View view){
        String text = "";
        for (Kontakt k : Kontakt.listAll(Kontakt.class)){
            text += k.getFullKontakt();
        }
        textBox.setText(text);
    }

    public void removeData(View view){
        delete(Integer.valueOf(delNumer.getText().toString()));
    }
    public void delete(int id){
        try {
            Kontakt kontakt = Kontakt.findById(Kontakt.class, id);
            kontakt.delete();
        } catch (NullPointerException e){
            showWrongCredentialsDialog("Błąd", "Nie ma takiego id");
        }
    }


}

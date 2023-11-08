import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.calculator.R;
public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private double operand1 = Double.NaN;
    private String pendingOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        // Set click listeners for calculator buttons
        setButtonClickListener(R.id.n0, "0");
        setButtonClickListener(R.id.n1, "1");
        setButtonClickListener(R.id.n2, "2");
        setButtonClickListener(R.id.n3, "3");
        setButtonClickListener(R.id.n4, "4");
        setButtonClickListener(R.id.n5, "5");
        setButtonClickListener(R.id.n6, "6");
        setButtonClickListener(R.id.n7, "7");
        setButtonClickListener(R.id.n8, "8");
        setButtonClickListener(R.id.n9, "9");

        setButtonClickListener(R.id.plus, "+");
        setButtonClickListener(R.id.minus, "-");
        setButtonClickListener(R.id.multiply, "*");
        setButtonClickListener(R.id.divide, "/");
        setButtonClickListener(R.id.percent, "%");

        setButtonClickListener(R.id.root, "√");
        setButtonClickListener(R.id.oneDividedByX, "1/x");

        setButtonClickListener(R.id.MC, "MC");
        setButtonClickListener(R.id.MR, "MR");
        setButtonClickListener(R.id.MS, "MS");
        setButtonClickListener(R.id.Mplus, "M+");
        setButtonClickListener(R.id.Mminus, "M-");

        setButtonClickListener(R.id.back, "Back");
        setButtonClickListener(R.id.CE, "CE");
        setButtonClickListener(R.id.C, "C");
        setButtonClickListener(R.id.plusMinus, "±");

        // Special handling for the dot and equals buttons
        Button dotButton = findViewById(R.id.dot);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDotClick();
            }
        });

        Button equalsButton = findViewById(R.id.equals);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsClick();
            }
        });
    }

    private void setButtonClickListener(int buttonId, final String buttonText) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(buttonText);
            }
        });
    }

    private void onDotClick() {
        // Handle the dot button click
        String currentText = editText.getText().toString();

        // Check if the current text already contains a dot
        if (!currentText.contains(".")) {
            editText.setText(currentText + ".");
        }
    }

    private void onEqualsClick() {
        // Handle the equals button click
        String currentText = editText.getText().toString();

        try {
            double operand2 = Double.parseDouble(currentText);

            // Perform the calculation based on the pendingOperation and operand1
            if (pendingOperation.equals("+")) {
                operand1 += operand2;
            } else if (pendingOperation.equals("-")) {
                operand1 -= operand2;
            } else if (pendingOperation.equals("*")) {
                operand1 *= operand2;
            } else if (pendingOperation.equals("/")) {
                operand1 /= operand2;
            } else if (pendingOperation.equals("%")) {
                operand1 %= operand2;
            }

            // Update the result in the EditText
            editText.setText(String.valueOf(operand1));
            pendingOperation = "";
        } catch (NumberFormatException e) {
            // Handle invalid input
            editText.setText("Error");
        }
    }

    private void onButtonClick(String buttonText) {
        // Handle the button click for digits and operators
        String currentText = editText.getText().toString();

        if (currentText.equals("0") || currentText.equals("Error")) {
            editText.setText(buttonText);
        } else {
            editText.setText(currentText + buttonText);
        }

        if (isOperator(buttonText)) {
            // Set the pendingOperation when an operator is clicked
            pendingOperation = buttonText;
            operand1 = Double.parseDouble(currentText);
        }
    }

    private boolean isOperator(String text) {
        return text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("%");
    }
}
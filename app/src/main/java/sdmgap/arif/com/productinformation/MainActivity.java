package sdmgap.arif.com.productinformation;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    private EditText product_Name, product_Brand, product_Code, product_Color,
            product_Size, product_Price, product_Description, product_Id;

    private Spinner product_type;

    private Button add_Info, view_Info, update_Info, delete_Info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


        product_Name = findViewById(R.id.product_Name);
        product_Brand = findViewById(R.id.product_Brand);
        product_Code = findViewById(R.id.product_Code);
        product_Color = findViewById(R.id.product_Color);
        product_Size = findViewById(R.id.product_Size);
        product_Price = findViewById(R.id.product_Price);
        product_Description = findViewById(R.id.product_Description);
        product_type = findViewById(R.id.product_Type);
        product_Id = findViewById(R.id.product_Id);


        add_Info = findViewById(R.id.add_Info);
        view_Info = findViewById(R.id.view_Info);
        update_Info = findViewById(R.id.update_Info);
        delete_Info = findViewById(R.id.delete_Info);


        AddInfo();
        viewInfo();
        UpdateInfo();
        DeleteInfo();

    }

    public void AddInfo() {
        add_Info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean error = false;

                        String name = product_Name.getText().toString();
                        if (name.isEmpty()) {
                            product_Name.setError("Product Name is Empty");
                            error = true;
                        } else if (name.length() < 2) {
                            product_Name.setError("Product Name is too Short");
                            error = true;
                        }


                        String brand = product_Brand.getText().toString();
                        if (brand.isEmpty()) {
                            product_Brand.setError("Product Band is Empty");
                            error = true;
                        } else if (brand.length() < 2) {
                            product_Name.setError("Product Brand is too Short");
                            error = true;
                        }


                        String code = product_Code.getText().toString();
                        if (code.isEmpty()) {
                            product_Code.setError("Product Code is missing");
                            error = true;
                        } else if (code.length() < 2) {
                            product_Code.setError("Product Code is too Short");
                            error = true;
                        }

                        String color = product_Color.getText().toString();
                        if (color.isEmpty()) {
                            product_Color.setError("Product Color is missing");
                            error = true;
                        } else if (color.length() < 2) {
                            product_Color.setError("Product Color is too Short");
                            error = true;
                        }


                        String size = product_Size.getText().toString();
                        if (size.isEmpty()) {
                            product_Size.setError("Product Size is missing");
                            error = true;
                        } else if (size.length() < 1) {
                            product_Name.setError("Product Size Unavailable");
                            error = true;
                        }


                        String price = product_Price.getText().toString();
                        if (price.isEmpty()) {
                            product_Price.setError("Product Price is missing");
                            error = true;
                        } else if (price.length() < 2) {
                            product_Price.setError("Product Price is Low");
                            error = true;
                        }

                        String description = product_Description.getText().toString();
                        if (description.isEmpty()) {
                            product_Description.setError("Product Description is missing");
                            error = true;
                        } else if (description.length() < 5) {
                            product_Description.setError("Product Description is too Short");
                            error = true;
                        }

                        String type = (String) product_type.getSelectedItem();

                        if (error==true) {
                            Toast.makeText(MainActivity.this, "Data Missing", Toast.LENGTH_LONG).show();
                        } else
                            {

                            boolean isInserted = myDb.insertData(name, brand, code, color, size, price, description,type);

                            if (isInserted==true)
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                            }
                    }
                }
        );
    }

    public void viewInfo() {
        view_Info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDb.getAllData();
                        if (result.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("Id :" + result.getString(0) + "\n");
                            buffer.append("Name :" + result.getString(1) + "\n");
                            buffer.append("Brand :" + result.getString(2) + "\n");
                            buffer.append("Code :" + result.getString(3) + "\n");
                            buffer.append("Color :" + result.getString(4) + "\n");
                            buffer.append("Size :" + result.getString(5) + "\n");
                            buffer.append("Price :" + result.getString(6) + "\n");
                            buffer.append("Description :" + result.getString(7) + "\n");
                            buffer.append("Type :" + result.getString(8) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }


    public void UpdateInfo() {
        update_Info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean error = false;

                        String name = product_Name.getText().toString();
                        if (name.isEmpty()) {
                            product_Name.setError("Product Name is Empty");
                            error = true;
                        } else if (name.length() < 2) {
                            product_Name.setError("Product Name is too Short");
                            error = true;
                        }


                        String brand = product_Brand.getText().toString();
                        if (brand.isEmpty()) {
                            product_Brand.setError("Product Band is Empty");
                            error = true;
                        } else if (brand.length() < 2) {
                            product_Name.setError("Product Brand is too Short");
                            error = true;
                        }


                        String code = product_Code.getText().toString();
                        if (code.isEmpty()) {
                            product_Code.setError("Product Code is missing");
                            error = true;
                        } else if (code.length() < 2) {
                            product_Code.setError("Product Code is too Short");
                            error = true;
                        }

                        String color = product_Color.getText().toString();
                        if (color.isEmpty()) {
                            product_Color.setError("Product Color is missing");
                            error = true;
                        } else if (color.length() < 2) {
                            product_Color.setError("Product Color is too Short");
                            error = true;
                        }


                        String size = product_Size.getText().toString();
                        if (size.isEmpty()) {
                            product_Size.setError("Product Size is missing");
                            error = true;
                        } else if (size.length() < 1) {
                            product_Name.setError("Product Size Unavailable");
                            error = true;
                        }


                        String price = product_Price.getText().toString();
                        if (price.isEmpty()) {
                            product_Price.setError("Product Price is missing");
                            error = true;
                        } else if (price.length() < 2) {
                            product_Price.setError("Product Price is Low");
                            error = true;
                        }

                        String description = product_Description.getText().toString();
                        if (description.isEmpty()) {
                            product_Description.setError("Product Description is missing");
                            error = true;
                        } else if (description.length() < 5) {
                            product_Description.setError("Product Description is too Short");
                            error = true;
                        }

                        String type = (String) product_type.getSelectedItem();

                        String id = product_Id.getText().toString();
                        if (id.isEmpty()) {
                            product_Id.setError("Product Id is missing");
                            error = true;
                        }


                        if (error==true) {
                            Toast.makeText(MainActivity.this, "Data Missing", Toast.LENGTH_LONG).show();
                        } else
                        {
                            boolean isUpdate = myDb.updateData(id, name, brand, code, color, size, price, description,type);

                            if (isUpdate==true)
                                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }

    public void DeleteInfo() {
        delete_Info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean error = false;

                        String id = product_Id.getText().toString();
                        if (id.isEmpty()) {
                            product_Id.setError("Product Id is missing");
                            error = true;
                        }


                        if (error==true) {
                            Toast.makeText(MainActivity.this, "You didn't input any Id", Toast.LENGTH_LONG).show();
                        } else {

                            Integer deletedRows = myDb.deleteData(id);
                            if (deletedRows > 0)
                                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
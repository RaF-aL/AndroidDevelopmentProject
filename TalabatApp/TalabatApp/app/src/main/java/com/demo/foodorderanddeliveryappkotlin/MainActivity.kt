package com.demo.foodorderanddeliveryappkotlin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.foodorderanddeliveryappkotlin.adapter.FragmentAdapter
import com.demo.foodorderanddeliveryappkotlin.adapter.RestaurantListAdapter
import com.demo.foodorderanddeliveryappkotlin.models.RestaurentModel
import com.google.gson.Gson
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), RestaurantListAdapter.RestaurantListClickListener {
    private lateinit var fragmentContainer: RecyclerView
    private lateinit var fragmentAdapter: FragmentAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sign Up Components:
        val firstName: EditText? = findViewById(R.id.FirstName)
        val lastName : EditText? = findViewById(R.id.editTextLastName)
        val signUpEmail : EditText? = findViewById(R.id.editTextEmail)
        val signUpPassword : EditText? = findViewById(R.id.editTextPassword)
        val phoneNumber: EditText? = findViewById(R.id.editTextPhoneNumber)
        val signUpbutton: TextView? = findViewById(R.id.buttonSignUp)
        val linkToSignIn: TextView? = findViewById(R.id.linkToSignIn)

        // Sign in Components:
        val linkToSignUp: TextView? = findViewById(R.id.linkToSignUp)
        val signInEmail: TextView? = findViewById(R.id.editTextEmailSignIn)
        val signInPassword:EditText? = findViewById(R.id.editTextPasswordSignIn)
        val signInButton:Button? = findViewById(R.id.buttonSignIn)

        // Boolean flag to check if the user is authenticated.
        var check_access:Boolean = false


        fragmentContainer = findViewById(R.id.recyclerViewRestaurant)
        fragmentContainer?.layoutManager = LinearLayoutManager(this)
        fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentContainer?.adapter = fragmentAdapter

        // Add your logic to show the initial fragment (logo fragment) here
        // fragmentAdapter.addFragment(LogoFragment())

        // Show the sign-in fragment initially
        val signInFragment = SignInFragment()
        fragmentAdapter.addFragment(signInFragment)



    // Implement any necessary methods or callbacks for handling navigation between fragments
    // ...
        if(check_access) {
            val actionBar: ActionBar? = supportActionBar
            actionBar?.setTitle("Restaurant List")

            val restaurantModel = getRestaurantData()
            initRecyclerView(restaurantModel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.user_account_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.updateUserInfo ->Toast.makeText(this, "item 1 selected", Toast.LENGTH_SHORT).show()
            R.id.deleteUser -> Toast.makeText(this, "item 2 selected", Toast.LENGTH_SHORT).show()
        }
        return true
    }
    private fun initRecyclerView(restaurantList: List<RestaurentModel?>?) {
        val recyclerViewRestaurant = findViewById<RecyclerView>(R.id.recyclerViewRestaurant)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
        val adapter = RestaurantListAdapter(restaurantList, this)
        recyclerViewRestaurant.adapter =adapter
    }

    private fun getRestaurantData(): List<RestaurentModel?>? {
        val inputStream: InputStream = resources.openRawResource(R.raw.restaurent)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n : Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)

            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val restaurantModel = gson.fromJson<Array<RestaurentModel>>(jsonStr, Array<RestaurentModel>::class.java).toList()

        return restaurantModel
    }

    override fun onItemClick(restaurantModel: RestaurentModel) {
       val intent = Intent(this@MainActivity, RestaurantMenuActivity::class.java)
        intent.putExtra("RestaurantModel", restaurantModel)
        startActivity(intent)
    }
}

private operator fun Unit.invoke(signUpFragment: SignUpFragment) {

}

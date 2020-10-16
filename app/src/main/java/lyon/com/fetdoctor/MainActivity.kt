package lyon.com.fetdoctor

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import lyon.com.fetdoctor.Base.BaseActivity
import lyon.com.fetdoctor.Tool.Tool


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    val TAG = this::class.java.simpleName;
    lateinit var context:Context
    val mainFragment: MainFragment = MainFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(R.layout.activity_main)
        titleColor=R.drawable.side_nav_bar;
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {


        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        val header: View = navView.getHeaderView(0)
        val version : TextView = header.findViewById(R.id.nav_header_title);
        var ver = "ver:"+ Tool.getVersionName(context)+"("+ Tool.getVersionCode(context)+")"
        version.text =ver
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        init()

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_float_view -> {
                AlertDialog.Builder(context).setTitle(context.getString(R.string.action_settings)).setMessage(context.getString(R.string.menu_FloatView)).setCancelable(true).create().show()

            }
            R.id.nav_about_me -> {
                var alert =  AlertDialog.Builder(context)
                alert.setTitle(context.getString(R.string.menu_aboutMe)).setMessage(context.getString(R.string.about_Me_sub)).setCancelable(true)
                alert.setPositiveButton(getString(R.string.contactMe),object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Tool.shareEmail(context,"lejiteyu@gmail.com",
                            "Android App "+getString(R.string.app_name)+" ver:"+ Tool.getVersionName(context)+"("+Tool.getVersionCode(context)+")",
                            "")
                    }
                })
                alert.create()
                alert.show()
            }
            R.id.nav_score ->{
                val intent =  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + getPackageName()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.no_brows), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    fun init(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, mainFragment)
        transaction.commit()
    }




}

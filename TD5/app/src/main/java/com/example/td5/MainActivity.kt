package com.example.td5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var contacts: MutableList<Contact> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        var rvContacts: RecyclerView = findViewById(R.id.rvContacts)

        contacts.add(Contact("Jean Pierre", "Pernault","https://www.jpptv.fr/build/images/app/jpp.6d637de2.png"))
        contacts.add(Contact("Jeanne", "D'arc","https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"))
        contacts.add(Contact("Pierre", "Menez","https://www.gala.fr/imgre/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fgal.2Fvar.2Fgal.2Fstorage.2Fimages.2Fmedia.2Fimages.2Factu.2Fphotos_on_ne_parle_que_de_ca.2Fpierre_menes.2F1498447-1-fre-FR.2Fpierre_menes.2Ejpg/480x480/quality/80/pierre-menes-fustige-le-racisme-des-guignols.jpg"))
        contacts.add(Contact("Arthur", "Rimbaut","https://upload.wikimedia.org/wikipedia/commons/1/1c/Rimbaud.PNG"))
        contacts.add(Contact("Richard", "Coeur de Lion","https://i2.wp.com/www.histoire-normandie.fr/wp-content/uploads/2016/08/richard_coeur_lion_blondel.jpg"))
        contacts.add(Contact("Zinedine", "Zidane","https://content.api.news/v3/images/bin/e356073fb990d855b75ada74cf22a2d4"))
        contacts.add(Contact("Yannick", "Noah","https://www.programme-tv.net/imgre/fit/https.3A.2F.2Fprd2-tel-epg-img.2Es3-eu-west-1.2Eamazonaws.2Ecom.2FproviderPerson.2F2a835c717ac9ab21.2Ejpeg/300x300/quality/80/yannick-noah.jpeg"))

        var adapter: ContactAdapter = ContactAdapter(contacts)

        rvContacts.adapter = adapter

        rvContacts.layoutManager = LinearLayoutManager(this)
    }

}
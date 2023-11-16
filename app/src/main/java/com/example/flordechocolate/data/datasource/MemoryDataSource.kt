package com.example.flordechocolate.data.datasource

import com.example.flordechocolate.R
import com.example.flordechocolate.data.models.CompanyModel
import com.example.flordechocolate.data.models.ProductsItemModel
import com.example.flordechocolate.data.models.ServiceItemModel

class MemoryDataSource {
    fun service(): List<ServiceItemModel> {
        return listOf(
            ServiceItemModel(
                id = "1", title = "Postres",
                R.drawable.pastel.toString()
            ),
            ServiceItemModel(
                id = "2", title = "Café",
                R.drawable.cafe.toString()
            ),
            ServiceItemModel(
                id = "3", title = "Sándwich",
                R.drawable.sandwich.toString()
            ),
            ServiceItemModel(
                id = "4", title = "Jugos",
                R.drawable.jugo.toString()
            )
        )
    }

    fun company(): CompanyModel {
        return CompanyModel(
            "1", "Flor de chocolate", 3.433151, -76.545684
        )

    }

    fun product(): List<ProductsItemModel> {
        return listOf(
            ProductsItemModel(id = "1", name = "Cheesecake de frutos rojos", products_category = "Postres", amount_three = "$8.500",
                R.drawable.ch_frutos_rojos.toString(), star = 4, description_two = "Delicioso cheesecake a base de queso y galleta, acompañado con salsa de frutos rojos."),
            ProductsItemModel(id = "2", name = "Café irlandés", products_category = "Café", amount_three = "$7.900",
                R.drawable.irlandes.toString(), star = 5, description_two = "Bebida a base de café, whisky y leche cremada."),
            ProductsItemModel(id = "3", name = "Sándwich de cerdo BBQ", products_category = "Sándwich", amount_three = "$13.800",
                R.drawable.sandich_bbq.toString(), star = 5, description_two = "Sándwich en pan artellano con vegetales, carne de cerdo y salsa de la casa."),
            ProductsItemModel(id = "4", name = "Postre de milo", products_category = "Postres", amount_three = "$7.000",
                R.drawable.postre_de_milo.toString(), star = 3, description_two = "Delicioso postre a base de crema de leche y milo."),
            ProductsItemModel(id = "5", name = "Frappé de café", products_category = "Café", amount_three = "$7.500",
                R.drawable.frappe_de_cafe.toString(), star = 5, description_two = "Bebida a base de hielo, café y leche en polvo."),
            ProductsItemModel(id = "6", name = "Jugo de mango", products_category = "Jugos", amount_three = "$6.000",
                R.drawable.jugo_de_naranja.toString(), star = 4, description_two = "Delicioso jugo natural de mango."),
            ProductsItemModel(id = "7", name = "Americano", products_category = "Café", amount_three = "$3.800",
                R.drawable.cafe_americano.toString(), star = 5, description_two = "Bebida a base de café expresso y agua."),
            ProductsItemModel(id = "8", name = "Cheesecake de oreo", products_category = "Postres", amount_three = "$8.500",
                R.drawable.cheesecake_de_oreo.toString(), star = 5, description_two = "Delicioso cheesecake a base de queso y galleta oreo."),
            ProductsItemModel(id = "9", name = "Jugo de fresa", products_category = "Jugos", amount_three = "$6.000",
                R.drawable.jugo_de_fresa.toString(), star = 2, description_two = "Delicioso jugo natural de fresa."),
            ProductsItemModel(id = "10", name = "Sándwich colombiano", products_category = "Sándwich", amount_three = "$13.800",
                R.drawable.sandwich_carne_mechada.toString(), star = 5, description_two = "Sándwich en pan artellano con vegetales, carne desmechada, ahogado y salsa de la casa."),
            ProductsItemModel(id = "11", name = "Torta Red Velvet", products_category = "Postres", amount_three = "$7.500",
                R.drawable.torta_red_velvet.toString(), star = 5, description_two = "Deliciosa torta a base de cocoa y ganash de queso."),
            ProductsItemModel(id = "12", name = "Expreso", products_category = "Café", amount_three = "$3.800",
                R.drawable.cafe_expesso.toString(), star = 2, description_two = "Café expresso"),
            ProductsItemModel(id = "13", name = "Flan de leche asada", products_category = "Postres", amount_three = "$6.500",
                R.drawable.flan_leche_asada.toString(), star = 4, description_two = "Delicioso flan de leche asada acompañado con salsa de caramelo."),
            ProductsItemModel(id = "14", name = "Jugo de guanábana", products_category = "Jugos", amount_three = "$6.000",
                R.drawable.jugo_de_guanabana.toString(), star = 3, description_two = "Delicioso jugo natural de guanábana."),
            ProductsItemModel(id = "15", name = "Capuchino", products_category = "Café", amount_three = "$6.000",
                R.drawable.capuchino.toString(), star = 5, description_two = "Bebida a base de expresso y leche cremada."),
            ProductsItemModel(id = "16", name = "Torta de chocolate", products_category = "Postres", amount_three = "$6.900",
                R.drawable.torta_de_chocolate.toString(), star = 5, description_two = "Deliciosa torta de chocolate."),
            ProductsItemModel(id = "17", name = "Sándwich pernil de cerdo", products_category = "Sándwich", amount_three = "$15.500",
                R.drawable.sandwich_pernil_de_cerdo.toString(), star = 5, description_two = "Sándwich en pan artellano con vegetales, carne de cerdo y salsa de la casa."),
            ProductsItemModel(id = "18", name = "Jugo de mora", products_category = "Jugos", amount_three = "$6.000",
                R.drawable.jugo_de_mora.toString(), star = 4, description_two = "Delicioso jugo natutral de mora."),
            ProductsItemModel(id = "19", name = "Torta de naranja", products_category = "Postres", amount_three = "$6.500",
                R.drawable.torta_de_naranja.toString(), star = 3, description_two = "Deliciosa torta de naranja."),
            ProductsItemModel(id = "20", name = "Sándwich de pollo", products_category = "Sándwich", amount_three = "$13.800",
                R.drawable.sandwich_de_pollo.toString(), star = 5, description_two = "Sándwich en pan artellano con vegetales, pollo con champiñones y salsa de la casa."),
            ProductsItemModel(id = "21", name = "Mocachino", products_category = "Café", amount_three = "$7.000",
                R.drawable.mocachino.toString(), star = 5, description_two = "Bebida a base de café expresso, leche cremada y chocolate."),
            ProductsItemModel(id = "22", name = "Tiramisú", products_category = "Postres", amount_three = "$8.500",
                R.drawable.postre_tiramisu.toString(), star = 5, description_two = "Delicioso postre a base de café y crema de whisky."),
            ProductsItemModel(id = "23", name = "Macchiato", products_category = "Café", amount_three = "$4.200",
                R.drawable.machiatto.toString(), star = 2, description_two = "Bebida a base de expresso y poca de leche."),
            ProductsItemModel(id = "24", name = "Postre ópera", products_category = "Postres", amount_three = "$7.900",
                R.drawable.postre_opera.toString(), star = 5, description_two = "Delicioso postre a base de crema de café y crema de chocolate.")
        )
    }
}
package kz.bakhus.mechtatestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import kz.bakhus.catalog.main.CatalogFeatureUI
import kz.bakhus.catalog.main.CatalogMain
import kz.bakhus.mechtatestapp.ui.theme.MechtaTestAppTheme
import kz.bakhus.product.ProductMain
import kz.bakhus.product.ProductScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MechtaTestAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = CatalogFeatureUI,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable<CatalogFeatureUI>() {
                            CatalogMain(
                                onProductClick = { productId,title ->
                                    navController.navigate(ProductScreen(productId,title))
                                }
                            )
                        }
                        composable<ProductScreen>(
                        ) {
                            val args = it.toRoute<ProductScreen>()
                            ProductMain(productId = args,

                                onBackClick = { navController.popBackStack() })
                        }
                    }
                }


            }
        }
    }
}

package com.leguer.app.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.leguer.app.R




@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.logotipo_leguer_02 else R.drawable.logotipo_leguer_s_apeke),
        contentDescription = "Logo login",
        modifier = modifier
    )
}
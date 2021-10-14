package com.cold0.crier.social.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cold0.crier.social.theme.CrierSocialTheme

@Preview
@Composable
fun HomeScreenPreview() {
    CrierSocialTheme {
        Surface() {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun HomeScreenPreviewDark() {
    CrierSocialTheme(true) {
        Surface() {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    CrierSocialTheme {
        Surface() {
            SearchScreen()
        }
    }
}

@Preview
@Composable
fun SearchScreenPreviewDark() {
    CrierSocialTheme(true) {
        Surface() {
            SearchScreen()
        }
    }
}

@Preview
@Composable
fun NotificationScreenPreview() {
    CrierSocialTheme {
        Surface() {
            NotificationScreen()
        }
    }
}

@Preview
@Composable
fun NotificationScreenPreviewDark() {
    CrierSocialTheme(true) {
        Surface() {
            SearchScreen()
        }
    }
}

@Preview
@Composable
fun MessageScreenPreview() {
    CrierSocialTheme(false) {
        Surface() {
            MessageScreen()
        }
    }
}

@Preview
@Composable
fun MessageScreenPreviewDark() {
    CrierSocialTheme(true) {
        Surface() {
            MessageScreen()
        }
    }
}


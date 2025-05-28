# MainActivity 난독화 제외
-keep class com.imsec.silentbull.MainActivity { *; }

# 안드로이드 액티비티 기본 설정 (보통 기본 템플릿에 포함)
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# Kotlin 사용 시 추가
-keepclassmembers class kotlin.Metadata { *; }


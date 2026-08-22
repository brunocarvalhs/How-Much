package br.com.brunocarvalhs.howmuch.feature.auth.app.domain.usecase;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AuthConfigUseCase_Factory implements Factory<AuthConfigUseCase> {
  private final Provider<Context> contextProvider;

  private final Provider<GoogleProviderUseCase> googleProvider;

  private AuthConfigUseCase_Factory(Provider<Context> contextProvider,
      Provider<GoogleProviderUseCase> googleProvider) {
    this.contextProvider = contextProvider;
    this.googleProvider = googleProvider;
  }

  @Override
  public AuthConfigUseCase get() {
    return newInstance(contextProvider.get(), googleProvider.get());
  }

  public static AuthConfigUseCase_Factory create(Provider<Context> contextProvider,
      Provider<GoogleProviderUseCase> googleProvider) {
    return new AuthConfigUseCase_Factory(contextProvider, googleProvider);
  }

  public static AuthConfigUseCase newInstance(Context context,
      GoogleProviderUseCase googleProvider) {
    return new AuthConfigUseCase(context, googleProvider);
  }
}

package br.com.brunocarvalhs.howmuch.feature.auth.app.presentation.viewmodel;

import br.com.brunocarvalhs.howmuch.core.domain.services.AuthService;
import br.com.brunocarvalhs.howmuch.feature.auth.app.domain.usecase.AuthConfigUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<AuthService> authServiceProvider;

  private final Provider<AuthConfigUseCase> authConfigProvider;

  private LoginViewModel_Factory(Provider<AuthService> authServiceProvider,
      Provider<AuthConfigUseCase> authConfigProvider) {
    this.authServiceProvider = authServiceProvider;
    this.authConfigProvider = authConfigProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(authServiceProvider.get(), authConfigProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<AuthService> authServiceProvider,
      Provider<AuthConfigUseCase> authConfigProvider) {
    return new LoginViewModel_Factory(authServiceProvider, authConfigProvider);
  }

  public static LoginViewModel newInstance(AuthService authService, AuthConfigUseCase authConfig) {
    return new LoginViewModel(authService, authConfig);
  }
}

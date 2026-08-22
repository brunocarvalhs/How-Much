package br.com.brunocarvalhs.howmuch.feature.profile.presentation.viewmodel;

import br.com.brunocarvalhs.howmuch.core.domain.repository.UserRepository;
import br.com.brunocarvalhs.howmuch.core.domain.services.AuthService;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<AuthService> authServiceProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private ProfileViewModel_Factory(Provider<AuthService> authServiceProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.authServiceProvider = authServiceProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(authServiceProvider.get(), userRepositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<AuthService> authServiceProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new ProfileViewModel_Factory(authServiceProvider, userRepositoryProvider);
  }

  public static ProfileViewModel newInstance(AuthService authService,
      UserRepository userRepository) {
    return new ProfileViewModel(authService, userRepository);
  }
}

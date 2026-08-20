package br.com.brunocarvalhs.howmuch.feature.auth.presentation.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class WelcomeViewModel_Factory implements Factory<WelcomeViewModel> {
  @Override
  public WelcomeViewModel get() {
    return newInstance();
  }

  public static WelcomeViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WelcomeViewModel newInstance() {
    return new WelcomeViewModel();
  }

  private static final class InstanceHolder {
    static final WelcomeViewModel_Factory INSTANCE = new WelcomeViewModel_Factory();
  }
}

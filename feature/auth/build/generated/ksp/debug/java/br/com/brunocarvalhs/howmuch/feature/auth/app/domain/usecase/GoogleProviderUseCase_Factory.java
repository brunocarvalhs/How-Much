package br.com.brunocarvalhs.howmuch.feature.auth.app.domain.usecase;

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
public final class GoogleProviderUseCase_Factory implements Factory<GoogleProviderUseCase> {
  @Override
  public GoogleProviderUseCase get() {
    return newInstance();
  }

  public static GoogleProviderUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GoogleProviderUseCase newInstance() {
    return new GoogleProviderUseCase();
  }

  private static final class InstanceHolder {
    static final GoogleProviderUseCase_Factory INSTANCE = new GoogleProviderUseCase_Factory();
  }
}

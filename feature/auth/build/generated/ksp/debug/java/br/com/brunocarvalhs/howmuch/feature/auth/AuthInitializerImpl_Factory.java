package br.com.brunocarvalhs.howmuch.feature.auth;

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
public final class AuthInitializerImpl_Factory implements Factory<AuthInitializerImpl> {
  @Override
  public AuthInitializerImpl get() {
    return newInstance();
  }

  public static AuthInitializerImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthInitializerImpl newInstance() {
    return new AuthInitializerImpl();
  }

  private static final class InstanceHolder {
    static final AuthInitializerImpl_Factory INSTANCE = new AuthInitializerImpl_Factory();
  }
}

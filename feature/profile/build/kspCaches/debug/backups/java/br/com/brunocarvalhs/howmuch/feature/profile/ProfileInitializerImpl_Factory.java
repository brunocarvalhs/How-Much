package br.com.brunocarvalhs.howmuch.feature.profile;

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
public final class ProfileInitializerImpl_Factory implements Factory<ProfileInitializerImpl> {
  @Override
  public ProfileInitializerImpl get() {
    return newInstance();
  }

  public static ProfileInitializerImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProfileInitializerImpl newInstance() {
    return new ProfileInitializerImpl();
  }

  private static final class InstanceHolder {
    static final ProfileInitializerImpl_Factory INSTANCE = new ProfileInitializerImpl_Factory();
  }
}

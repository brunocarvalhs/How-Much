package br.com.brunocarvalhs.howmuch.feature.cart;

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
public final class CartInitializerImpl_Factory implements Factory<CartInitializerImpl> {
  @Override
  public CartInitializerImpl get() {
    return newInstance();
  }

  public static CartInitializerImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CartInitializerImpl newInstance() {
    return new CartInitializerImpl();
  }

  private static final class InstanceHolder {
    static final CartInitializerImpl_Factory INSTANCE = new CartInitializerImpl_Factory();
  }
}

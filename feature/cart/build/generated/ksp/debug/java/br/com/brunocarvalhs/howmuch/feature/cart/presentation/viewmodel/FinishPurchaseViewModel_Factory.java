package br.com.brunocarvalhs.howmuch.feature.cart.presentation.viewmodel;

import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.ShoppingUpdateUseCase;
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
public final class FinishPurchaseViewModel_Factory implements Factory<FinishPurchaseViewModel> {
  private final Provider<ShoppingUpdateUseCase> shoppingUpdateUseCaseProvider;

  private FinishPurchaseViewModel_Factory(
      Provider<ShoppingUpdateUseCase> shoppingUpdateUseCaseProvider) {
    this.shoppingUpdateUseCaseProvider = shoppingUpdateUseCaseProvider;
  }

  @Override
  public FinishPurchaseViewModel get() {
    return newInstance(shoppingUpdateUseCaseProvider.get());
  }

  public static FinishPurchaseViewModel_Factory create(
      Provider<ShoppingUpdateUseCase> shoppingUpdateUseCaseProvider) {
    return new FinishPurchaseViewModel_Factory(shoppingUpdateUseCaseProvider);
  }

  public static FinishPurchaseViewModel newInstance(ShoppingUpdateUseCase shoppingUpdateUseCase) {
    return new FinishPurchaseViewModel(shoppingUpdateUseCase);
  }
}

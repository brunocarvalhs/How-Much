package br.com.brunocarvalhs.howmuch.feature.cart.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.ProductsUseCase;
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
public final class ConfirmItemViewModel_Factory implements Factory<ConfirmItemViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ProductsUseCase> useCaseProvider;

  private ConfirmItemViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ProductsUseCase> useCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.useCaseProvider = useCaseProvider;
  }

  @Override
  public ConfirmItemViewModel get() {
    return newInstance(savedStateHandleProvider.get(), useCaseProvider.get());
  }

  public static ConfirmItemViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ProductsUseCase> useCaseProvider) {
    return new ConfirmItemViewModel_Factory(savedStateHandleProvider, useCaseProvider);
  }

  public static ConfirmItemViewModel newInstance(SavedStateHandle savedStateHandle,
      ProductsUseCase useCase) {
    return new ConfirmItemViewModel(savedStateHandle, useCase);
  }
}

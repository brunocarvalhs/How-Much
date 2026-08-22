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
public final class EditItemViewModel_Factory implements Factory<EditItemViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ProductsUseCase> useCaseProvider;

  private EditItemViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ProductsUseCase> useCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.useCaseProvider = useCaseProvider;
  }

  @Override
  public EditItemViewModel get() {
    return newInstance(savedStateHandleProvider.get(), useCaseProvider.get());
  }

  public static EditItemViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ProductsUseCase> useCaseProvider) {
    return new EditItemViewModel_Factory(savedStateHandleProvider, useCaseProvider);
  }

  public static EditItemViewModel newInstance(SavedStateHandle savedStateHandle,
      ProductsUseCase useCase) {
    return new EditItemViewModel(savedStateHandle, useCase);
  }
}

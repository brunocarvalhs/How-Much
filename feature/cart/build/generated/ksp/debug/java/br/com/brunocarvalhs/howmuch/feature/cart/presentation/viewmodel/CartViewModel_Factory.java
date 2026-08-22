package br.com.brunocarvalhs.howmuch.feature.cart.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import br.com.brunocarvalhs.howmuch.core.domain.repository.ShoppingRepository;
import br.com.brunocarvalhs.howmuch.feature.chat.domain.usecase.CartAssistantUseCase;
import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.GetQuestionSuggestionsUseCase;
import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.ProductsUseCase;
import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.ShoppingClearPurchasedUseCase;
import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.SortProductsUseCase;
import br.com.brunocarvalhs.howmuch.feature.settings.app.domain.usecase.GetSettingsUseCase;
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
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ShoppingRepository> repositoryProvider;

  private final Provider<ProductsUseCase> useCaseProvider;

  private final Provider<CartAssistantUseCase> assistantUseCaseProvider;

  private final Provider<GetQuestionSuggestionsUseCase> getQuestionSuggestionsUseCaseProvider;

  private final Provider<ShoppingClearPurchasedUseCase> clearPurchasedUseCaseProvider;

  private final Provider<GetSettingsUseCase> getSettingsUseCaseProvider;

  private final Provider<SortProductsUseCase> sortProductsUseCaseProvider;

  private CartViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ShoppingRepository> repositoryProvider, Provider<ProductsUseCase> useCaseProvider,
      Provider<CartAssistantUseCase> assistantUseCaseProvider,
      Provider<GetQuestionSuggestionsUseCase> getQuestionSuggestionsUseCaseProvider,
      Provider<ShoppingClearPurchasedUseCase> clearPurchasedUseCaseProvider,
      Provider<GetSettingsUseCase> getSettingsUseCaseProvider,
      Provider<SortProductsUseCase> sortProductsUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.repositoryProvider = repositoryProvider;
    this.useCaseProvider = useCaseProvider;
    this.assistantUseCaseProvider = assistantUseCaseProvider;
    this.getQuestionSuggestionsUseCaseProvider = getQuestionSuggestionsUseCaseProvider;
    this.clearPurchasedUseCaseProvider = clearPurchasedUseCaseProvider;
    this.getSettingsUseCaseProvider = getSettingsUseCaseProvider;
    this.sortProductsUseCaseProvider = sortProductsUseCaseProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(savedStateHandleProvider.get(), repositoryProvider.get(), useCaseProvider.get(), assistantUseCaseProvider.get(), getQuestionSuggestionsUseCaseProvider.get(), clearPurchasedUseCaseProvider.get(), getSettingsUseCaseProvider.get(), sortProductsUseCaseProvider.get());
  }

  public static CartViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ShoppingRepository> repositoryProvider, Provider<ProductsUseCase> useCaseProvider,
      Provider<CartAssistantUseCase> assistantUseCaseProvider,
      Provider<GetQuestionSuggestionsUseCase> getQuestionSuggestionsUseCaseProvider,
      Provider<ShoppingClearPurchasedUseCase> clearPurchasedUseCaseProvider,
      Provider<GetSettingsUseCase> getSettingsUseCaseProvider,
      Provider<SortProductsUseCase> sortProductsUseCaseProvider) {
    return new CartViewModel_Factory(savedStateHandleProvider, repositoryProvider, useCaseProvider, assistantUseCaseProvider, getQuestionSuggestionsUseCaseProvider, clearPurchasedUseCaseProvider, getSettingsUseCaseProvider, sortProductsUseCaseProvider);
  }

  public static CartViewModel newInstance(SavedStateHandle savedStateHandle,
      ShoppingRepository repository, ProductsUseCase useCase, CartAssistantUseCase assistantUseCase,
      GetQuestionSuggestionsUseCase getQuestionSuggestionsUseCase,
      ShoppingClearPurchasedUseCase clearPurchasedUseCase, GetSettingsUseCase getSettingsUseCase,
      SortProductsUseCase sortProductsUseCase) {
    return new CartViewModel(savedStateHandle, repository, useCase, assistantUseCase, getQuestionSuggestionsUseCase, clearPurchasedUseCase, getSettingsUseCase, sortProductsUseCase);
  }
}

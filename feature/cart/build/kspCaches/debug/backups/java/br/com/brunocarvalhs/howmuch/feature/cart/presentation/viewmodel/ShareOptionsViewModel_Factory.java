package br.com.brunocarvalhs.howmuch.feature.cart.presentation.viewmodel;

import br.com.brunocarvalhs.howmuch.feature.products.app.domain.usecase.ShareShoppingUseCase;
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
public final class ShareOptionsViewModel_Factory implements Factory<ShareOptionsViewModel> {
  private final Provider<ShareShoppingUseCase> shareShoppingUseCaseProvider;

  private ShareOptionsViewModel_Factory(
      Provider<ShareShoppingUseCase> shareShoppingUseCaseProvider) {
    this.shareShoppingUseCaseProvider = shareShoppingUseCaseProvider;
  }

  @Override
  public ShareOptionsViewModel get() {
    return newInstance(shareShoppingUseCaseProvider.get());
  }

  public static ShareOptionsViewModel_Factory create(
      Provider<ShareShoppingUseCase> shareShoppingUseCaseProvider) {
    return new ShareOptionsViewModel_Factory(shareShoppingUseCaseProvider);
  }

  public static ShareOptionsViewModel newInstance(ShareShoppingUseCase shareShoppingUseCase) {
    return new ShareOptionsViewModel(shareShoppingUseCase);
  }
}

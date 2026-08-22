package br.com.brunocarvalhs.howmuch.feature.chat.presentation.viewmodel;

import br.com.brunocarvalhs.howmuch.feature.chat.domain.usecase.CartAssistantUseCase;
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
public final class AiChatViewModel_Factory implements Factory<AiChatViewModel> {
  private final Provider<CartAssistantUseCase> assistantUseCaseProvider;

  private AiChatViewModel_Factory(Provider<CartAssistantUseCase> assistantUseCaseProvider) {
    this.assistantUseCaseProvider = assistantUseCaseProvider;
  }

  @Override
  public AiChatViewModel get() {
    return newInstance(assistantUseCaseProvider.get());
  }

  public static AiChatViewModel_Factory create(
      Provider<CartAssistantUseCase> assistantUseCaseProvider) {
    return new AiChatViewModel_Factory(assistantUseCaseProvider);
  }

  public static AiChatViewModel newInstance(CartAssistantUseCase assistantUseCase) {
    return new AiChatViewModel(assistantUseCase);
  }
}

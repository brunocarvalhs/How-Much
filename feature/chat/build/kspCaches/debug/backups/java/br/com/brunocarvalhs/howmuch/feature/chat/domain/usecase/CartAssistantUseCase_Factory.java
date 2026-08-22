package br.com.brunocarvalhs.howmuch.feature.chat.domain.usecase;

import br.com.brunocarvalhs.howmuch.core.ai.contract.AiAgentFactory;
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
public final class CartAssistantUseCase_Factory implements Factory<CartAssistantUseCase> {
  private final Provider<AiAgentFactory> agentFactoryProvider;

  private final Provider<GetSettingsUseCase> getSettingsUseCaseProvider;

  private CartAssistantUseCase_Factory(Provider<AiAgentFactory> agentFactoryProvider,
      Provider<GetSettingsUseCase> getSettingsUseCaseProvider) {
    this.agentFactoryProvider = agentFactoryProvider;
    this.getSettingsUseCaseProvider = getSettingsUseCaseProvider;
  }

  @Override
  public CartAssistantUseCase get() {
    return newInstance(agentFactoryProvider.get(), getSettingsUseCaseProvider.get());
  }

  public static CartAssistantUseCase_Factory create(Provider<AiAgentFactory> agentFactoryProvider,
      Provider<GetSettingsUseCase> getSettingsUseCaseProvider) {
    return new CartAssistantUseCase_Factory(agentFactoryProvider, getSettingsUseCaseProvider);
  }

  public static CartAssistantUseCase newInstance(AiAgentFactory agentFactory,
      GetSettingsUseCase getSettingsUseCase) {
    return new CartAssistantUseCase(agentFactory, getSettingsUseCase);
  }
}

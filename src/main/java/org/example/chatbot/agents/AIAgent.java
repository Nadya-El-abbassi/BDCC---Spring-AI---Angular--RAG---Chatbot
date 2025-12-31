package org.example.chatbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AIAgent {

    private final ChatClient chatClient;

    public AIAgent(ChatClient.Builder builder,
                   ToolCallbackProvider tools) {

        // Affiche tous les outils disponibles pour debug
        Arrays.stream(tools.getToolCallbacks()).forEach(toolCallback -> {
            System.out.println("----------------------");
            System.out.println("Tool : " + toolCallback.getToolDefinition());
            System.out.println("----------------------");
        });

        this.chatClient = builder
                .defaultSystem("""
                        Vous êtes un assistant expert sur les employés.
                        Vous devez répondre uniquement en utilisant les outils disponibles.
                        Ne devinez jamais les réponses.
                        Si vous ne savez pas, répondez JE NE SAIS PAS.
                        """)
                // On n’utilise pas de mémoire pour éviter les erreurs
                //.defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    @Retryable(value = Exception.class, maxAttempts = 3)
    public String askAgent(String query) {
        try {
            String response = chatClient.prompt()
                    .user("""
                        Question : %s
                        Répondez uniquement en utilisant les outils.
                        Si aucune réponse n'est disponible, répondez JE NE SAIS PAS.
                    """.formatted(query))
                    .call()
                    .content();

            System.out.println("Prompt envoyé : " + query);
            System.out.println("Réponse reçue : " + response);

            return response;
        } catch (Exception e) {
            System.err.println("Erreur AI : " + e.getMessage());
            return "JE NE SAIS PAS";
        }
    }
}

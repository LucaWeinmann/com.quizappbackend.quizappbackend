package com.quizappbackend.utils

import com.quizappbackend.model.databases.mongodb.documents.MongoQuestionnaire
import com.quizappbackend.model.databases.mongodb.documents.MongoFilledQuestion
import com.quizappbackend.model.databases.mongodb.documents.MongoFilledQuestionnaire
import kotlin.random.Random

object RandomFilledQuestionnaireCreatorUtil {

    fun generateRandomData(
        userId: String,
        mongoQuestionnaires : List<MongoQuestionnaire>,
    ): List<MongoFilledQuestionnaire> {
        return mongoQuestionnaires.map {
            MongoFilledQuestionnaire(
                questionnaireId = it.id,
                userId = userId,
                questions = it.questions.map { question ->
                    val filledAnswers = question.answers.filter { Random.nextBoolean() }.map { answer -> answer.id }
                    MongoFilledQuestion(questionId = question.id, selectedAnswerIds = filledAnswers)
                }
            )
        }.toList()
    }
}